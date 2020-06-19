package impl.queue

import api.queue.IntQueue
import java.util.*

internal class ApprovedIntQueue(private val maxSize: Int) : IntQueue {
    private val queue = ArrayDeque<Int>()

    override fun add(e: Int) {
        if (size == maxSize) {
            throw IllegalStateException("Queue is full")
        }
        queue.add(e)
    }

    override fun element(): Int? = queue.peek()

    override fun remove(): Int? = queue.poll()

    override fun getSize(): Int = queue.size
}