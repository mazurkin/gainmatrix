package com.gainmatrix.lib.checkstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Thhe check requires that all properties are followed by empty line
 */
public class PropertyFollowedEmptyLineCheck extends Check {

    private static final int[] DEFAULT_TOKENS = {
            TokenTypes.CLASS_DEF,
            TokenTypes.INTERFACE_DEF
    };

    @Override
    public int[] getDefaultTokens() {
        return DEFAULT_TOKENS;
    }

    @Override
    public void visitToken(DetailAST ast) {
        // Search for object block
        DetailAST objectBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
        if (objectBlock == null) {
            return;
        }

        // Search for variables
        for (DetailAST child = objectBlock.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == TokenTypes.VARIABLE_DEF) {
                handleVariable(child);
            }
        }
    }

    private void handleVariable(DetailAST variable) {
        // Search for ";" separator
        DetailAST separator = null;

        for (DetailAST child = variable.getFirstChild(); child != null; child = child.getNextSibling()) {
            if ((child.getType() == TokenTypes.SEMI) && (child.getText().equals(";"))) {
                separator = child;
            }
        }

        if (separator == null) {
            return;
        }

        // Check the next line after variable definition
        String[] lines = getLines();
        int thisLineIndex = separator.getLineNo() - 1;
        int nextLineIndex = thisLineIndex + 1;

        if (nextLineIndex < lines.length) {
            String nextLine = lines[nextLineIndex];
            if ((nextLine != null) && (!nextLine.trim().isEmpty())) {
                log(variable, "There must be an empty line after variable definition");
            }
        }
    }

}
