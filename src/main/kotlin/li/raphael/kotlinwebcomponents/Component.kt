package li.raphael.kotlinwebcomponents

import kotlinx.html.FlowContent
import kotlin.reflect.KVisibility.PUBLIC
import kotlin.reflect.full.declaredMemberProperties

@ComponentMarker
abstract class Component<C>(val context: C) {
    protected val children = arrayListOf<Component<C>>()

    protected fun <T : Component<C>> initComponent(component: T, init: (T.() -> Unit)? = null): T {
        if (init != null) component.init()
        children.add(component)
        return component
    }

    protected fun renderChildren(tagConsumer: FlowContent) {
        children.forEach { child ->
            child.apply {
                tagConsumer.render()
            }
        }
    }

    protected open fun FlowContent.render() = renderChildren(this)

    override fun toString(): String {
        val builder = StringBuilder()
        treeToString(builder, "")
        return builder.toString()
    }

    private fun treeToString(builder: StringBuilder, indent: String) {
        builder.append("$indent<${this::class.simpleName}${printTreeAttributes()}")
        if (children.isEmpty()) builder.append(" />\n")
        else builder.append(">\n")
        children.forEach { child ->
            child.treeToString(builder, "$indent  ")
        }
        if (children.isNotEmpty()) builder.append("$indent</${this::class.simpleName}>\n")
    }

    private fun printTreeAttributes(): String {
        val builder = StringBuilder()
        this::class.declaredMemberProperties
            .filter { it.visibility == PUBLIC }
            .forEach { field ->
                val attr = field.name
                val value = field.call(this)
                builder.append(" $attr=\"$value\"")
            }
        return builder.toString()
    }

}