package cz.visualio.citygame.utlis

/**
 * Created by stanislav on 7/13/17.
 */
data class IdIncrementer(var id: Long = 0)
{
    fun getNextId(): Long{
        return id++
    }
}