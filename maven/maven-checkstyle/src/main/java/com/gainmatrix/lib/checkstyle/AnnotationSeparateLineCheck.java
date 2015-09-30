package com.gainmatrix.lib.checkstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Check is an annotation placed on a separate line
 */
public class AnnotationSeparateLineCheck extends Check {

    private static final int[] DEFAULT_TOKENS = {
            TokenTypes.ANNOTATION
    };

    @Override
    public int[] getDefaultTokens() {
        return DEFAULT_TOKENS;
    }

    @Override
    public void visitToken(DetailAST annotationAst) {
        // Get next sibling
        DetailAST nextSibling = annotationAst.getNextSibling();
        if (nextSibling == null) {
            return;
        }

        // Check the next line after variable definition

        if (annotationAst.getLineNo() == nextSibling.getLineNo()) {
            log(annotationAst, "Annotation must be placed on a separate line");
        }
    }

}
