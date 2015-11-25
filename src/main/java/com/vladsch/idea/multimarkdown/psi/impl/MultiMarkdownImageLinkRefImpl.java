/*
 * Copyright (c) 2015-2015 Vladimir Schneider <vladimir.schneider@gmail.com>, all rights reserved.
 *
 * This code is private property of the copyright holder and cannot be used without
 * having obtained a license or prior written permission of the of the copyright holder.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package com.vladsch.idea.multimarkdown.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.vladsch.idea.multimarkdown.MultiMarkdownPlugin;
import com.vladsch.idea.multimarkdown.MultiMarkdownProjectComponent;
import com.vladsch.idea.multimarkdown.psi.*;
import com.vladsch.idea.multimarkdown.util.PathInfo;
import com.vladsch.idea.multimarkdown.util.WikiLinkRef;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class MultiMarkdownImageLinkRefImpl extends MultiMarkdownNamedElementImpl implements MultiMarkdownImageLinkRef {
    private static final Logger logger = Logger.getLogger(MultiMarkdownImageLinkRefImpl.class);
    protected static final String MISSING_ELEMENT_NAME_SPACE = "image::";

    @NotNull
    @Override
    public String getMissingElementNamespace() {
        assert getParent() instanceof MultiMarkdownExplicitLink;
        return ((MultiMarkdownImageLink) getParent()).getMissingElementNameSpace(MISSING_ELEMENT_NAME_SPACE, false);
    }

    public MultiMarkdownImageLinkRefImpl(ASTNode node) {
        super(node);
    }

    @Override
    public MultiMarkdownReference createReference(@NotNull TextRange textRange) {
        return new MultiMarkdownReferenceImageLinkRef(this, textRange);
    }

    @Override
    public String getDisplayName() {
        return getParent() instanceof MultiMarkdownImageLink ? ((MultiMarkdownImageLink) getParent()).getDisplayName() : getFileName();
    }

    @Override
    public String getFileName() {
        return getName() == null ? "" : getName();
    }

    @Override
    public String getFileNameWithAnchor() {
        return getNameWithAnchor();
    }

    @Override
    public String getNameWithAnchor() {
        return MultiMarkdownPsiImplUtil.getLinkRefTextWithAnchor(getParent());
    }

    @Override
    public MultiMarkdownNamedElement handleContentChange(String newContent) throws IncorrectOperationException {
        String newName = WikiLinkRef.convertFileToLink(new PathInfo(newContent).getFileNameNoExt());
        MultiMarkdownProjectComponent projectComponent = MultiMarkdownPlugin.getProjectComponent(getProject());
        if (projectComponent == null) return this;

        return (MultiMarkdownNamedElement) setName(newName, projectComponent.getRefactoringRenameFlags(REASON_FILE_RENAMED));
    }

    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement context) {
        return false;
    }

    @Override
    public boolean isInplaceRenameAvailable(PsiElement context) {
        return false;
    }

    @Override
    public PsiElement setName(@NotNull String newName, int renameFlags) {
        MultiMarkdownProjectComponent projectComponent = MultiMarkdownPlugin.getProjectComponent(getProject());
        if (projectComponent == null) return this;

        if (projectComponent.getRefactoringRenameFlags() != RENAME_NO_FLAGS) renameFlags = projectComponent.getRefactoringRenameFlags();
        renameFlags &= ~RENAME_KEEP_ANCHOR;

        return MultiMarkdownPsiImplUtil.setName(this, newName, renameFlags);
    }

    @Override
    public String toString() {
        return "IMAGE_LINK_REF '" + getName() + "' " + super.hashCode();
    }
}
