package services;

import interfaces.Term_Condition;

public class Term_conditionService {
    private Term_Condition term_Condition=new Term_Condition();
    public Term_conditionService(){

    }
    public Term_Condition getTerm_Condition() {
        return term_Condition;
    }
    public void setTerm_Condition(Term_Condition term_Condition) {
        this.term_Condition = term_Condition;
    }
    
    
}
