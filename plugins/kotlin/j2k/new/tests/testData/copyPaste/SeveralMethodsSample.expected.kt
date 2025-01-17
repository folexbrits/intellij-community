// ERROR: Unresolved reference: PsiElement
// ERROR: Unresolved reference: UsageInfo
// ERROR: Unresolved reference: PsiElement
// ERROR: Unresolved reference: JetPsiUtil
// ERROR: Unresolved reference: JetNamedFunction
// ERROR: Unresolved reference: JetProperty
// ERROR: Unresolved reference: BindingContext
// ERROR: Unresolved reference: AnalyzerFacadeWithCache
// ERROR: Unresolved reference: DeclarationDescriptor
// ERROR: Unresolved reference: BindingContext
// ERROR: Unresolved reference: CallableMemberDescriptor
// ERROR: Unresolved reference: DeclarationDescriptor
// ERROR: Unresolved reference: ClassDescriptor
// ERROR: Unresolved reference: JetBundle
// ERROR: Unresolved reference: DescriptorRenderer
// ERROR: Unresolved reference: IdeDescriptorRenderers
// ERROR: Unresolved reference: PsiMethod
// ERROR: Unresolved reference: JetRefactoringUtil
// ERROR: Unresolved reference: PsiMethod
// ERROR: Unresolved reference: UsageInfo
// ERROR: Unresolved reference: UsageInfo
// ERROR: Unresolved reference: myChecked
// ERROR: Unresolved reference: myChecked
// ERROR: Unresolved reference: myOverridingMethods
class A {
    fun someOther() = false

    private fun formatElement(element: PsiElement): String {
        var element: PsiElement = element
        element = JetPsiUtil.ascendIfPropertyAccessor(element)
        if (element is JetNamedFunction || element is JetProperty) {
            val bindingContext: BindingContext =
                AnalyzerFacadeWithCache.analyzeFileWithCache(element.getContainingJetFile()).getBindingContext()
            val declarationDescriptor: DeclarationDescriptor =
                bindingContext.get(BindingContext.DECLARATION_TO_DESCRIPTOR, element)
            if (declarationDescriptor is CallableMemberDescriptor) {
                val containingDescriptor: DeclarationDescriptor = declarationDescriptor.getContainingDeclaration()
                if (containingDescriptor is ClassDescriptor) {
                    return JetBundle.message(
                        "override.declaration.x.in.y",
                        DescriptorRenderer.COMPACT.render(declarationDescriptor),
                        IdeDescriptorRenderers.SOURCE_CODE_SHORT_NAMES_NO_ANNOTATIONS.render(containingDescriptor)
                    )
                }
            }
        }
        assert(element is PsiMethod) { ("Method accepts only kotlin functions/properties and java methods, but '" + element.getText()).toString() + "' was found" }
        return JetRefactoringUtil.formatPsiMethod(element as PsiMethod, true, false)
    }

    protected fun getDimensionServiceKey(): String {
        return "#org.jetbrains.kotlin.idea.refactoring.safeDelete.KotlinOverridingDialog"
    }

    fun getSelected(): ArrayList<UsageInfo> {
        val result: ArrayList<UsageInfo> = ArrayList<UsageInfo>()
        for (i in 0 until myChecked.length) {
            if (myChecked.get(i)) {
                result.add(myOverridingMethods.get(i))
            }
        }
        return result
    }
}
