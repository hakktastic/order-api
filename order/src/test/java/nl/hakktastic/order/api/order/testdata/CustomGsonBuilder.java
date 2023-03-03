package nl.hakktastic.order.api.order.testdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.hakktastic.order.api.order.testdata.gson.LocalDateTimeDeserializer;
import nl.hakktastic.order.api.order.testdata.gson.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * Custom Gson builder
 */
public final class CustomGsonBuilder {

    /**
     * Get {@link Gson} instance.
     *
     * <p>
     * Since JDK 16, access of JDK internals using reflection is not allowed (see <a href="https://bugs.openjdk.java.net/browse/JDK-8256358">JDK-8256358</a>).
     * In order to make {@link LocalDateTime} fields accessible, custom {@link com.google.gson.TypeAdapter} needs to be defined.
     * </p>
     *
     * @return a gson instance with custom type adapters configured
     */
    public static Gson getGsonInstance(){

        var gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        return gsonBuilder.setPrettyPrinting().create();
    }
}
