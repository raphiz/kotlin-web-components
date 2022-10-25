package li.raphael.kotlinwebcomponents

import kotlinx.html.FlowContent
import kotlin.reflect.KVisibility.PUBLIC
import kotlin.reflect.full.declaredMemberProperties

interface SemanticComponent {
    val children: List<SemanticComponent>
    val elementAttributes: Map<String, Any?>
        get() = this::class.declaredMemberProperties
            .filter { it.visibility == PUBLIC }
            .associate { it.name to it.call(this) }
    val elementName: String
        get() = this::class.simpleName!!
}

@ComponentMarker
abstract class Component<C>(val context: C) : SemanticComponent {
    override val children = arrayListOf<Component<C>>()

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

    override fun toString() = TreePrinter.print(this)
}