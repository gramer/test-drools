package com.gramer.drools.sample;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class Account {

    private Integer balance;

    public Account() {
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Account(Integer balance) {
        super();
        this.balance = balance;
    }

    public void withdraw(int money) {
        balance -= money;
    }

    public static final void main(String[] args) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("com/gramer/drools/sample/myrule.drl"), ResourceType.DRL);
        KnowledgeBase kbase = kbuilder.newKnowledgeBase();
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        Account account = new Account(200);
        account.withdraw(150);
        ksession.execute(account);
    }

}
