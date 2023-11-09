package com.iliadavidovich.moviehub.Classes
import java.util.UUID
class Comment (val film: Film, val notes: String, val id: UUID =UUID.randomUUID() )