# Workout List App - Coding Assignment

## Overview

Welcome! This assignment is designed to evaluate your Android development skills, including your ability to work with JSON data, build clean UI, implement user interactions, and write testable, maintainable code.

You'll build an Android app that reads a local JSON file and displays a list of workouts. Users should be able to edit workout details.
You'll have one week to complete this assignment. While we understand everyone works at their own pace, we recommend spending no more than 4–6 hours on it. We’re not looking for a fully polished or production-ready app.  So, focus on clean, understandable code and demonstrating your thought process.

---

## ✨ Requirements

### Core Functionality

1. **Load & Display Workouts**
   - Load the provided JSON file (`workouts.json`).
   - *See SecondFragment*
   - Display the list of workouts in a scrollable list.
   - *See SecondFragment*

2. **Edit Workouts**
   - Users should be able to tap on a workout to view and edit its details in a separate screen.
   - *See SecondFragment and EditWorkoutFragment*
   - Changes should be retained in-memory during the session.
   - *See emulator .json if ran*
---

## 🛠 Technical Expectations

- Use **Kotlin** or **Java** as the base language.
- *I used Kotlin*
- Use **Jetpack Compose** or **XML** for UI.
- *I used XML*
- Add **unit tests** and/or **UI tests** to validate core functionality.
- *I made both*

---

## 🎯 Bonus Points

- Persist workout changes.
- *done, use an emulator to check, used gson originally, made a manual parser for messy .json data provided*
- Deliver a polished and responsive UI.
- *polished and responsive*
- Scalable code structure.
- *scalable if needed new buttons and workouts is infinitely scalable right now*

---

## 🧪 Testing

Please include tests for:
- JSON parsing and data modeling
- *EditWorkoutFragmentTest.kt*
- Core logic (e.g., editing and updating workout details)
- *EditWorkoutFragmentTest.kt*
- UI interaction (if applicable)
- *FirstFragmentTest.kt*

---

## 📝 Developer Notes

Please fill out the section below before submitting your solution.

### What did you focus on and why?

I focused on the UX of the app; I made sureto ensure that gestures and controls were intuitive and responsive for users. An example, I used an integer input for the workout time and a spinner for difficulty, allowing quick adjustments. Larger buttons were implemented to make the app easier to use during exercise. I also kept the flow for adding and editing exercises relatively linear, avoiding unnecessary buttons and clutter.

### What would you improve with more time?

With more time, I would have liked to add a specific workout builder, allowing users to add multiple exercise types (ie. cardio and yoga) into a single workout. Users would be able to view, update, and track the workouts they complete, improving functionality for end users. I would also have liked to enhance gestures, such as implementing a swipe to return to the exercise list because the app is so linear.

### How did you approach testing?

My approach to testing was focused on core functionality. For the UI, I verified that the app behaved correctly on the emulator with basic interaction tests. For storage, I ensured that adding, editing, and persisting workouts worked as expected. Since the app’s scope was limited, I prioritized testing features that directly impacted usability and data integrity.

---

## 📁 Provided Files

- `workouts.json`
- `README.md`

---

## 🚀 Submission Instructions

1. Complete the assignment and update this `README.md` with your responses above.
2. Upload your project to a private GitHub repository or share it via a zip file.
3. Make sure your project builds and runs cleanly.

Have fun building! 💪
