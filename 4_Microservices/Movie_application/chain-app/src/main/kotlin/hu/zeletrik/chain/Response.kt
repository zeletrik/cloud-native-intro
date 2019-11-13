package hu.zeletrik.chain

import hu.zeletrik.chain.data.entity.MovieEntity
import net.logstash.logback.encoder.org.apache.commons.lang3.mutable.Mutable
import java.util.*

data class Response(
        val userID: UUID,
        val favorites: MutableList<MovieEntity>
)