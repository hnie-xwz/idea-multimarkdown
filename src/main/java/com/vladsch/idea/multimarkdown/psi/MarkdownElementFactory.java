/*
 * Copyright (c) 2015-2015 Vladimir Schneider <vladimir.schneider@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * This file is based on the IntelliJ SimplePlugin tutorial
 *
 */
package com.vladsch.idea.multimarkdown.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.vladsch.idea.multimarkdown.MarkdownFileType;

public class MarkdownElementFactory {

    public static MarkdownProperty createProperty(Project project, String name, String value) {
        final MarkdownFile file = createFile(project, name + " = " + value);
        return (MarkdownProperty) file.getFirstChild();
    }

    public static MarkdownProperty createProperty(Project project, String name) {
        final MarkdownFile file = createFile(project, name);
        return (MarkdownProperty) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final MarkdownFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

    public static MarkdownFile createFile(Project project, String text) {
        String name = "dummy.Markdown";
        return (MarkdownFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, MarkdownFileType.INSTANCE, text);
    }
}

