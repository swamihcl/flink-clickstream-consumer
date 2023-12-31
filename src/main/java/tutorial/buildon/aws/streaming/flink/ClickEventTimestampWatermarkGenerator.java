package tutorial.buildon.aws.streaming.flink;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import tutorial.buildon.aws.streaming.avro.ClickEvent;

import javax.annotation.Nullable;

public class ClickEventTimestampWatermarkGenerator implements AssignerWithPeriodicWatermarks<ClickEvent> {
    private long currentMaxTimestamp;

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(currentMaxTimestamp);
    }

    @Override
    public long extractTimestamp(ClickEvent element, long previousElementTimestamp) {
        long timestamp = element.getEventtimestamp();
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
        return timestamp;
    }
}
