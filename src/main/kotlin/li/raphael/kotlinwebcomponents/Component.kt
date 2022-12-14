package li.raphael.kotlinwebcomponents

import kotlinx.html.FlowContent
import kotlinx.html.Tag


@ComponentMarker
abstract class Component<RECEIVER : Tag, CHILD_RECEIVER : Tag> : 
    SemanticComponent,
    HtmlComponent<RECEIVER, CHILD_RECEIVER> {
    override val children = arrayListOf<SemanticComponent>()

    fun <T : SemanticComponent> initComponent(component: T, init: (T.() -> Unit)? = null): T {
        if (init != null) component.init()
        children.add(component)
        return component
    }

    override fun toString() = TreePrinter.print(this)
}

typealias FlowContentComponent = Component<FlowContent, FlowContent>