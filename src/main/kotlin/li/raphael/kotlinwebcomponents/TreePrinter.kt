package li.raphael.kotlinwebcomponents

object TreePrinter {
    fun print(component: SemanticComponent): String {
        val builder = StringBuilder()
        treeToString(builder, component, "")
        return builder.toString()
    }

    private fun treeToString(builder: Appendable, component: SemanticComponent, indent: String) {
        builder.append("$indent<${component.elementName}")
        component.elementAttributes.forEach { (attr, value) ->
            builder.append(" $attr=\"$value\"")
        }
        if (component.children.isEmpty()) {
            builder.append(" />\n")
            return
        }
        builder.append(">\n")
        component.children.forEach { child ->
            treeToString(builder, child, "$indent  ")
        }
        if (component.children.isNotEmpty()) builder.append("$indent</${component.elementName}>\n")
    }
}