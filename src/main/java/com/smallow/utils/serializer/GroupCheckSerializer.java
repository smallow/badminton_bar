package com.smallow.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.utils.EnumUtil;

import java.io.IOException;

/**
 * Created by wanghuidong on 2017/9/9.
 */
public class GroupCheckSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(EnumUtil.getByCode(integer,BadmintonGroupCheckEnum.class).getMsg());
    }
}
