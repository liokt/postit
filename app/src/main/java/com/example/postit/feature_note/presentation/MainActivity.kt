package com.example.postit.feature_note.presentation

import PostitTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.postit.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.postit.feature_note.presentation.notes.components.NotesScreen
import com.example.postit.feature_note.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostitTheme {
                 androidx.compose.material.Surface(
                     color = MaterialTheme.colors.background
                 ) {
                     val navConntroller = rememberNavController()
                     NavHost(navController = navConntroller,
                     startDestination = Screen.NotesScreen.route
                     ){
                         composable(route = Screen.NotesScreen.route) {
                             NotesScreen(navController = navConntroller)
                         }
                         composable(
                             route = Screen.AddEditNoteScreen.route
                                     + "?noteId={noteId}&noteColor={noteColor}",
                             arguments = listOf(
                                 navArgument(
                                     name = "noteId"
                                 ) {
                                     type = NavType.IntType
                                     defaultValue = -1
                                 },
                                 navArgument(
                                     name = "noteColor"
                                 ) {
                                     type = NavType.IntType
                                     defaultValue = -1
                                 }
                             )
                         ) {
                             val color = it.arguments?.getInt("noteColor") ?: -1
                             AddEditNoteScreen(navController = navConntroller, noteColor = color)
                         }
                     }
                 }
            }
        }
    }
}