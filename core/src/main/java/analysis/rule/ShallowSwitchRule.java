package analysis.rule;

import analysis.AbstractRuleVisitor;
import analysis.BaseVisitor;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.stmt.SwitchStmt;
import model.Issue;
import model.IssueContext;
import refactor.refactorImpl.ShallowSwitchRefactor;

import java.util.List;

/**
 *  浅的Switch
 * */
public class ShallowSwitchRule extends AbstractRuleVisitor {
    private final int MAX_DEEP = 4;
    private final BaseVisitor<SwitchStmt> switchStmtVisitor = new BaseVisitor<SwitchStmt>(){
        @Override
        public void visit(SwitchStmt n, Object arg) {
            getList().add(n);
            super.visit(n, arg);
        }
    };
    @Override
    public IssueContext apply(List<CompilationUnit> units) {
        for (CompilationUnit unit : units) {
            collectSwitchStmt(unit);
        }
        check();
        return getContext();
    }

    private void collectSwitchStmt(CompilationUnit unit) {
        unit.accept(switchStmtVisitor,null);
    }

    private void check() {
        List<SwitchStmt> switchStmts = switchStmtVisitor.getList();
        for(SwitchStmt switchStmt : switchStmts){
            if(switchStmt.getEntries().size()<=MAX_DEEP){
                Issue issue = new Issue();
                issue.setUnitNode(switchStmt.findRootNode());
                issue.setIssueNode(switchStmt);
                getContext().getIssues().add(issue);
                issue.setRefactor(new ShallowSwitchRefactor());
                System.out.println(switchStmt);
            }
        }
    }

    private void hasBreakOrReturn(){

    }
}
