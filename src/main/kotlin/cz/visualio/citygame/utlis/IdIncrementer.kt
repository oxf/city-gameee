package cz.visualio.citygame.utlis

/**
 * Created by stanislav on 7/13/17.
 * Feature, solving problem with AutoIncrementing IDs
 */
data class IdIncrementer(var id: Long = 0)
{
    fun getNextId(): Long{
        return id++
    }
}