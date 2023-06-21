package de.hpi.dbs1

import JDBCExerciseJavaImplementation
import JDBCExerciseKotlinImplementation

annotation class ChosenImplementation(
    val value: Boolean
)

class NoSubmissionImplementationChosen : IllegalStateException(
    "Chose an implementation for your exercise by setting @ChosenImplementation(true)"
)

fun getChosenImplementation(): JDBCExercise {
    return listOf(
        JDBCExerciseJavaImplementation::class.java,
        JDBCExerciseKotlinImplementation::class.java,
    ).firstOrNull { clazz ->
        clazz.getAnnotation(ChosenImplementation::class.java)?.value ?: false
    }?.getDeclaredConstructor()?.newInstance()
        ?: throw NoSubmissionImplementationChosen()
}
