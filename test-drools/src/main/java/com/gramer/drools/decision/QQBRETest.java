package com.gramer.drools.decision;

import java.io.StringReader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.audit.WorkingMemoryFileLogger;
import org.drools.compiler.PackageBuilder;

public class QQBRETest {

    public static void main(String[] args) {
        try {
            // load up the rulebase
            RuleBase ruleBase = readDecisionTable();
            WorkingMemory workingMemory = ruleBase.newStatefulSession();

            WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger(workingMemory);
            logger.setFileName("C:/drools-audit");

            QuickQuoteInputProfile input = new QuickQuoteInputProfile();
            input.setAge(21);
            input.setFaceAmount(Double.valueOf(200000));
            input.setState("VA");
            input.setGender("MALE");
            input.setAdverseDiagnosis(false);

            SmokerProfile prof = new SmokerProfile();
            prof.setSmokerStatus("Y");

            input.setSmokingProf(prof);
            QuickQuoteResult result = new QuickQuoteResult();

            workingMemory.insert(input);
            workingMemory.setGlobal("result", result);
            workingMemory.fireAllRules();
            logger.writeToDisk();

            System.out.println(result.isEligible());

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static RuleBase readDecisionTable() throws Exception {
        // read in the source
        final SpreadsheetCompiler converter = new SpreadsheetCompiler();
        final String drl = converter.compile("QuickQuoteExample.xls", InputType.XLS);
        System.out.println(drl);
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl(new StringReader(drl));
        Package pkg = builder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(pkg);
        return ruleBase;
    }

}
