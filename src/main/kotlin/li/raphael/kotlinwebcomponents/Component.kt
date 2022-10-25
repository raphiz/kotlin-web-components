package li.raphael.kotlinwebcomponents

import kotlinx.html.Tag
import kotlin.reflect.KVisibility.PUBLIC
import kotlin.reflect.full.declaredMemberProperties

interface SemanticComponent {
    val children: List<SemanticComponent>
        get() = emptyList()
    val elementAttributes: Map<String, Any?>
        get() = this::class.declaredMemberProperties
            .filter { it.visibility == PUBLIC }
            .associate { it.name to it.call(this) }
    val elementName: String
        get() = this::class.simpleName!!
}

interface HtmlComponent<RECEIVER : Tag, CHILD_RECEIVER : Tag> : SemanticComponent {

    fun render(tag: RECEIVER)

    fun renderChildren(tagConsumer: CHILD_RECEIVER) {
        children
            .filterIsInstance<HtmlComponent<*, *>>()
            .forEach { child ->
                @Suppress("UNCHECKED_CAST")
                (child as HtmlComponent<CHILD_RECEIVER, *>).render(tagConsumer)
            }
    }

}


@ComponentMarker
abstract class Component<C, RECEIVER : Tag, CHILD_RECEIVER : Tag>(val context: C) : 
    SemanticComponent,
    HtmlComponent<RECEIVER, CHILD_RECEIVER> {
    override val children = arrayListOf<SemanticComponent>()

    protected fun <T : SemanticComponent> initComponent(component: T, init: (T.() -> Unit)? = null): T {
        if (init != null) component.init()
        children.add(component)
        return component
    }

    override fun toString() = TreePrinter.print(this)
}