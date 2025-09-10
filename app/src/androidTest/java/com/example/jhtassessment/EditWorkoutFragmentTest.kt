package com.example.jhtassessment

import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EditWorkoutFragmentTest {

    private lateinit var sampleJson: String

    /**
     * Setup for tests makes a pull workout with a few things from my own experience
     */
    @Before
    fun setup() {
        val exercises = JSONArray()
        val obj = JSONObject()
        obj.put("id", "61a8f2b9c4d5e6f789012345")
        obj.put("name", "Pull Workout")
        obj.put("equipment", "Barbells")
        obj.put("duration", 40)
        obj.put("difficulty", "Advanced")
        exercises.put(obj)
        sampleJson = exercises.toString()
    }

    @Test
    fun testParseExerciseJson() {
        val cleanedJson = sampleJson.replace(Regex(",\\s*([}\\]])"), "$1") // clean the json
        val jsonArray = JSONArray(cleanedJson) // json array
        val exercises = mutableListOf<Exercise>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            exercises.add(
                Exercise(
                    name = obj.optString("name", null),
                    id = obj.optString("id", null),
                    equipment = obj.optString("equipment", null),
                    duration = if (obj.has("duration")) obj.getInt("duration") else null,
                    difficulty = obj.optString("difficulty", null)
                )
            )
        }

        //Checks
        Assert.assertEquals(1, exercises.size)
        Assert.assertEquals("Pull Workout", exercises[0].name)
        Assert.assertEquals("Advanced", exercises[0].difficulty)
    }

    @Test
    fun testUpdateExerciseLogic() {
        val exercise = Exercise("Leg Day", "507f1f77bcf86cd799439011", "Barbells", 50, "Advanced")
        val updatedExercise = exercise.copy(duration = 50)

        //Checks
        Assert.assertEquals(50, updatedExercise.duration)
        Assert.assertEquals("Leg Day", updatedExercise.name)
    }
}