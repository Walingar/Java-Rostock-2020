# Homeworks for "Java Programming" course

# Task 4. Year weather stats

Your task is to implement the interfaces `api.weather.DayTemperatureInfoParser` and `api.weather.YearTemperatureStats`.
Create implemented instances in methods `impl.weather.DayTemperatureInfoParserFactory.getInstance` and `impl.weather.YearTemperatureStatsFactory.getInstance`.

`YearTemperatureStats` should be able to return information about:
  * temperature of the given day (`null` if info about this day isn't known); [return it in constant time]
  * average temperature for the given month (`null` if info about this month isn't known); [return it in constant time]
  * maximal temperature for each known month; [return it in `O(known months count)` time]
  * list of `DayTemperatureInfo` for the given month.
    This list should be sorted in ascending order.
    If some days had the same temperature then they should be sorted by the insertion order.

`YearTemperatureStats` should be able to update its state using given `DayTemperatureInfo` [in constant time].

`DayTemperatureInfoParser` should be able to create `DayTemperatureInfo` from the raw `String`. You can assume that this `String` has the following format: `day.month temperature`.