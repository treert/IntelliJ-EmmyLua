/*
 * Copyright (c) 2017. tangzx(love.tangzx@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tang.intellij.lua.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import com.tang.intellij.lua.lang.type.LuaString
import com.tang.intellij.lua.psi.*
import com.tang.intellij.lua.search.SearchContext

/**
 *
 * Created by tangzx on 2016/11/26.
 */
class LuaLiteralReference internal constructor(element: LuaLiteralExpr) : PsiReferenceBase<LuaLiteralExpr>(element) {
    private var pathString: String? = null
    private var range = TextRange.EMPTY_RANGE

    init {
        val luaString = LuaString.getContent(element.text)
        pathString = luaString.value

        if (pathString != null) {
            range = TextRange(1, pathString!!.length + 1)
        }
    }

    override fun getRangeInElement(): TextRange {
        return range
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return myElement.manager.areElementsEquivalent(element, resolve())
    }

    override fun resolve(): PsiElement? {
        return if (pathString == null) null else resolveRequireFile(pathString, myElement.project)
    }

    override fun getVariants(): Array<Any> {
        return emptyArray()
    }
}
