// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.kotlin.idea.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.analysis.api.KtAnalysisSession
import org.jetbrains.kotlin.analysis.api.calls.KtCall

interface KotlinCallHighlighterExtension {
    context(KtAnalysisSession)
    fun highlightCall(elementToHighlight: PsiElement, call: KtCall): TextAttributesKey?

    companion object {
        val EP_NAME = ExtensionPointName.create<KotlinCallHighlighterExtension>("org.jetbrains.kotlin.fir.highlighterExtension")
    }
}