package Interfaces;//Reprezentuje dowolny czujnik, który dostarcza dane określonego typu
// wykorzystując typy generyczne (np. Double dla temperatury,
// Boolean dla czujnika ruchu, String dla jakości powietrza, itd.).


public interface SensorDevice<T>{

    T readValue();
    String getUnit();
}
