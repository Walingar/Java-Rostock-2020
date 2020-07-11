package impl.weather

import api.weather.YearTemperatureStats
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.File
import java.time.Month

internal class DayTemperatureInfoImplParserTest {
    private fun readRawDataFromFile(fileName: String = "data/temperature.txt") =
        File(fileName).readLines(Charsets.UTF_8).map { it.trim() }

    private fun process(lines: List<String>): YearTemperatureStats {
        val parser = DayTemperatureInfoParserFactory.getInstance()
        val stats = YearTemperatureStatsFactory.getInstance()
        lines.forEach { line ->
            stats.updateStats(parser.parse(line) as DayTemperatureInfoImpl?)
        }
        return stats
    }

    @Test
    fun `parse single string`() {
        val stats = process(
            listOf(
                "31.12 0"
            )
        )
        assertEquals(0, stats.getTemperature(31, Month.DECEMBER).temperature)
    }

    @Test
    fun `parse negative temp`() {
        val stats = process(
            listOf(
                "31.12 -12"
            )
        )
        assertEquals(-12, stats.getTemperature(31, Month.DECEMBER).temperature)
    }

    @Test
    fun `parse few lines`() {
        val stats = process(
            listOf(
                "31.12 -12",
                "16.12 5"
            )
        )
        assertEquals(5, stats.getTemperature(16, Month.DECEMBER).temperature)
        assertEquals(-12, stats.getTemperature(31, Month.DECEMBER).temperature)
    }

    @Test
    fun `avg temperature`() {
        val data = readRawDataFromFile()
        val stats = process(data)
        assertEquals(
            "Incorrect average temp after parsing raw data: $data",
            1.93,
            stats.getAverageTemperature(Month.NOVEMBER),
            0.01
        )
    }
}