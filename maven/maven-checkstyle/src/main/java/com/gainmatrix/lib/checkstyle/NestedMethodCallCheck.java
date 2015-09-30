package com.gainmatrix.lib.checkstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * The check doesn't allow to have a nested method call with parameter
 */
public class NestedMethodCallCheck extends Check {

    private static final int[] DEFAULT_TOKENS = {
            TokenTypes.METHOD_CALL,
            TokenTypes.LITERAL_NEW
    };

    @Override
    public int[] getDefaultTokens() {
        return DEFAULT_TOKENS;
    }

    @Override
    public void visitToken(DetailAST ast) {
        // Search for object block
        DetailAST parametersBlock = ast.findFirstToken(TokenTypes.ELIST);
        if (parametersBlock == null) {
            return;
        }

        // Scan for nested method call
        searchNestedMethodCall(parametersBlock);
    }

    public void searchNestedMethodCall(DetailAST node) {
        for (DetailAST child=node.getFirstChild(); child != null; child=child.getNextSibling()) {
            switch (child.getType()) {
                case TokenTypes.METHOD_CALL:
                case TokenTypes.LITERAL_NEW:
                    analyseNestedMethodCall(child);
                    break;
                default:
                    searchNestedMethodCall(child);
                    break;
            }
        }
    }

    public void analyseNestedMethodCall(DetailAST methodCall) {
        // Get parameters block in nested method
        DetailAST parametersBlock = methodCall.findFirstToken(TokenTypes.ELIST);
        if (parametersBlock == null) {
            return;
        }

        // It must be empty
        if (parametersBlock.getNumberOfChildren() > 0) {
            log(methodCall, "Nested method call with parameters is not allowed");
        }
    }
}
