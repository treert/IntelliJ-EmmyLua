// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.psi.impl;

import java.util.List;

import com.tang.intellij.lua.project.LuaSettings;
import com.tang.intellij.lua.ty.TyAliasSubstitutor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.tang.intellij.lua.psi.LuaTypes.*;
import com.tang.intellij.lua.psi.*;
import com.tang.intellij.lua.search.SearchContext;
import com.tang.intellij.lua.ty.ITy;
import com.tang.intellij.lua.stubs.LuaExprPlaceStub;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import com.tang.intellij.lua.stubs.LuaExprStub;

public class LuaCallExprImpl extends LuaCallExprMixin implements LuaCallExpr {

  public LuaCallExprImpl(@NotNull LuaExprPlaceStub stub, @NotNull IStubElementType<?, ?> nodeType) {
    super(stub, nodeType);
  }

  public LuaCallExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public LuaCallExprImpl(@NotNull LuaExprPlaceStub stub, @NotNull IElementType type, @NotNull ASTNode node) {
    super(stub, type, node);
  }

  public void accept(@NotNull LuaVisitor visitor) {
    visitor.visitCallExpr(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LuaVisitor) accept((LuaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LuaArgs getArgs() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, LuaArgs.class));
  }

  @Override
  @NotNull
  public LuaExpr getExpr() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, LuaExpr.class));
  }

  @Override
  @NotNull
  public ITy guessParentType(@NotNull SearchContext context) {
    ITy t = LuaPsiImplUtilKt.guessParentType(this, context);
    return t;
  }

  @Override
  @NotNull
  public ITy guessType(SearchContext context) {
    ITy ty = SearchContext.Companion.infer(this, context);
    String typeName = ty.getDisplayName();
    if (typeName.contains("usefirststring")) {
      PsiElement p = getFirstStringArg();
      String str = LuaPsiImplUtilKt.getStringValue(p);
      if (str != "") {
        typeName = typeName.replace("usefirststring", str);
        ty = LuaPsiImplUtilKt.newType(typeName);
      }
    }
    else if(typeName.contains("usefirstname")) {
      PsiElement p = getFirstParamArg();
      String str = LuaPsiImplUtilKt.getParamStringValue(p);
      if (str != "") {
        typeName = typeName.replace("usefirstname", str);
        ty = LuaPsiImplUtilKt.newType(typeName);
      }
    }
    else if(typeName.contains("usefirstallname")) {
      PsiElement p = getFirstParamArg();
      String str = LuaPsiImplUtilKt.getParamAllStringValue(p);
      if (str != "") {
        typeName = typeName.replace("usefirstallname", str);
        ty = LuaPsiImplUtilKt.newType(typeName);
      }
    }
    else if (LuaPsiImplUtilKt.isClassLikeFunctionName(getExpr().getText())) {
      PsiElement p = getFirstStringArg();
      String str = LuaPsiImplUtilKt.getStringValue(p);
      if (str != "") {
        ITy spTy = getClassSuperName();
        if (!LuaPsiImplUtilKt.checkTyIsNull(spTy))
        {
          ty = LuaPsiImplUtilKt.newSuperType(str, spTy);
        }
        else
        {
          ty = LuaPsiImplUtilKt.newType(str);
        }
      }
    }

    ty = TyAliasSubstitutor.Companion.substitute(ty, context);
    if (ty == null)
    {
      ty = getExpr().guessType(context);
    }
    return ty;
  }

  @Nullable
  public PsiElement getFirstStringArg() {
    return LuaPsiImplUtilKt.getFirstStringArg(this);
  }

  @Nullable
  public ITy getClassSuperName() {
    return LuaPsiImplUtilKt.getSuperType(this, 2);
  }

  @Nullable
  public PsiElement getFirstParamArg() {
    return LuaPsiImplUtilKt.getFirstParamName(this);
  }

  @Override
  public boolean isMethodDotCall() {
    return LuaPsiImplUtilKt.isMethodDotCall(this);
  }

  @Override
  public boolean isMethodColonCall() {
    return LuaPsiImplUtilKt.isMethodColonCall(this);
  }

  @Override
  public boolean isFunctionCall() {
    return LuaPsiImplUtilKt.isFunctionCall(this);
  }

}
