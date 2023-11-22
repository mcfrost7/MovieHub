package com.iliadavidovich.moviehub.Classes
import org.w3c.dom.Comment
import java.util.UUID
class FilmComment (val film: Film, var notes: String, val id: UUID =UUID.randomUUID())