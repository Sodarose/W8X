package model;

import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import lombok.Data;
import refactor.Refactor;

/**
 * 坏味道模型数据
 * */
@Data
public class Issue {
    private String issueName;
    private String description;
    private Node issueNode;
    private String className;
    private CompilationUnit node;
    private String ruleName;
    private Refactor refactor;
}
