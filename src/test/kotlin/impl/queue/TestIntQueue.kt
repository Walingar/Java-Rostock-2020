package impl.queue

import api.queue.IntQueue

class TestIntQueue(maxSize: Int, private val testQueueList: List<IntQueue>) : IntQueue {
    private val approvedQueue = ApprovedIntQueue(maxSize)
    private val operations = mutableListOf<String>()

    override fun add(element: Int) {
        operations.add("add $element")
        approvedQueue.add(element).also {
            testQueueList.forEach { queue ->
                queue.add(element)
                checkQueueState(queue)
            }
        }
    }

    override fun getSize(): Int {
        operations.add("getSize")
        return approvedQueue.size.also { expected ->
            testQueueList.forEach { queue ->
                val actual = queue.size
                check(actual == expected) {
                    "Incorrect result during size. Expected $expected but got $actual".appendOperations()
                }
                checkQueueState(queue)
            }
        }
    }

    override fun element(): Int? {
        operations.add("element")
        return approvedQueue.element().also {
            testQueueList.forEach { queue ->
                queue.element()
                checkQueueState(queue)
            }
        }
    }

    override fun remove(): Int? {
        operations.add("remove")
        return approvedQueue.remove().also { expected ->
            testQueueList.forEach { queue ->
                val actual = queue.remove()
                check(actual == expected) {
                    "Incorrect result during remove. Expected $expected but got $actual".appendOperations()
                }
                checkQueueState(queue)
            }
        }
    }

    private fun checkQueueState(queue: IntQueue) {
        checkQueueSize(queue)
        checkQueueHead(queue)
    }

    private fun checkQueueHead(queue: IntQueue) {
        val actualHead = queue.element()
        val expectedHead = approvedQueue.element()
        check(actualHead == expectedHead) {
            "Actual head is $actualHead but expected $expectedHead".appendOperations()
        }
    }

    private fun checkQueueSize(queue: IntQueue) {
        val actualSize = queue.size
        val expectedSize = approvedQueue.size
        check(actualSize == expectedSize) {
            "Actual size is $actualSize but expected $expectedSize".appendOperations()
        }
    }

    private fun String.appendOperations() = buildString {
        appendln(this@appendOperations)
        appendln("Operations: $operations")
    }
}