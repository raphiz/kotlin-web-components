package li.raphael.kotlinwebcomponents

import kotlinx.html.Tag

interface HtmlComponent<RECEIVER : Tag, CHILD_RECEIVER : Tag> : SemanticComponent {

    fun RECEIVER.render()

    fun renderChildren(tagConsumer: CHILD_RECEIVER) {
        children
            .filterIsInstance<HtmlComponent<*, *>>()
            .forEach { child ->
                val childComponent: HtmlComponent<CHILD_RECEIVER, *> = @Suppress("UNCHECKED_CAST")
                (child as HtmlComponent<CHILD_RECEIVER, *>)
                childComponent.apply {
                    tagConsumer.apply {
                        render()
                    }
                }
            }
    }

}