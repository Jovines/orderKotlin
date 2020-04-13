import order.Item
import order.OrderScene

fun main() {
    val orderScene = OrderScene("默认", turnCanFixed = 3,maxColumn = 7)
    orderScene.apply {
        addItem(buildItem("张三",1 to 2,3 to 1))
        addItem(buildItem("张手机",1 to 2,3 to 4))
        addItem(buildItem("张啊哈",4 to 2,3 to 6))
        addItem(buildItem("张奥神",2 to 2,3 to 3))
        addItem(buildItem("三大",1 to 2,3 to 7))
        addItem(buildItem("阿达",3 to 2,3 to 2))
        addItem(buildItem("埃瓦尔多",2 to 2,4 to 7))
        addItem(buildItem("大地方",2 to 2,3 to 6))
        addItem(buildItem("染发",4 to 2,3 to 2))
        addItem(buildItem("富人",1 to 2,3 to 4))
    }
    orderScene.toString()
    print(orderScene)
}