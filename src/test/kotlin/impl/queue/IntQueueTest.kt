package impl.queue

import junit.framework.TestCase.*
import org.junit.Test
import java.util.*

internal class IntQueueTest {
    companion object {
        private const val MAX_SIZE = 20
    }

    private val rand = Random()

    private fun getTestQueueList() = listOf(
        ArrayQueueFactory.getInstance(MAX_SIZE),
        LinkedQueueFactory.getInstance(MAX_SIZE)
    )

    private fun getTestQueue() = TestIntQueue(MAX_SIZE, getTestQueueList())

    @Test
    fun emptyState() {
        getTestQueueList().forEach { queue ->
            assertEquals(0, queue.size)
            assertNull(queue.element())
            assertNull(queue.remove())
        }
    }

    @Test
    fun fewElements() {
        val testQueue = getTestQueue()
        testQueue.add(1)
        testQueue.add(2)
        testQueue.remove()
    }

    @Test
    fun maxSizeTest() {
        getTestQueueList().forEach { queue ->
            try {
                repeat(MAX_SIZE + 1) {
                    queue.add(0)
                }
                fail()
            } catch (e: IllegalStateException) {
                assertEquals(MAX_SIZE, queue.size)
            }
        }
    }

    @Test
    fun hugeTest() {
        repeat(100) {
            val testQueue = getTestQueue()
            repeat(10_000) {
                try {
                    testQueue.add(rand.nextInt())
                } catch (e: IllegalStateException) {
                    assertEquals(MAX_SIZE, testQueue.size)
                }
                if (rand.nextBoolean()) {
                    testQueue.remove()
                }
            }
        }
    }
}