package com.gainmatrix.lib.checkstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * The check requires a class has an empty line before any property or method definition
 */
public class ClassFollowedEmptyLineCheck extends Check {

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

        handleObjectBlock(objectBlock);
    }

    public void handleObjectBlock(DetailAST objectBlock) {
        // Search for open curly
        DetailAST curly = objectBlock.findFirstToken(TokenTypes.LCURLY);
        if (curly == null) {
            return;
        }

        // Check the next line after class definition
        String[] lines = getLines();
        int thisLineIndex = curly.getLineNo() - 1;
        int nextLineIndex = thisLineIndex + 1;

        if (nextLineIndex < lines.length) {
            String nextLine = lines[nextLineIndex];
            if ((nextLine != null) && (!nextLine.trim().isEmpty())) {
                log(curly, "There must be an empty line after class/interface header");
            }
        }
    }

}
