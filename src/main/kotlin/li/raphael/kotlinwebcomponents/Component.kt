package li.raphael.kotlinwebcomponents

import kotlinx.html.Tag


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