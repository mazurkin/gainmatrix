package com.gainmatrix.lib.checkstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TextBlock;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Deprecated annotation is not allowed without a comment
 */
public class DeprecatedWithoutJavadocCheck extends Check {

    private static final int[] DEFAULT_TOKENS = {
            TokenTypes.ANNOTATION
    };

    @Override
    public int[] getDefaultTokens() {
        return DEFAULT_TOKENS;
    }

    @Override
    public void visitToken(DetailAST annotationAst) {
        DetailAST tokenAst = annotationAst.findFirstToken(TokenTypes.IDENT);
        if ((tokenAst == null) || (!tokenAst.getText().equals("Deprecated"))) {
            return;
        }

        FileContents contents = getFileContents();
        TextBlock textBlock = contents.getJavadocBefore(annotationAst.getLineNo());

        if (textBlock == null) {
            log(annotationAst, "@Deprecated annotation must be preceeded by the JavaDoc with detailed descriptions");
        }
    }
}
