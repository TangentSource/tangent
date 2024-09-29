package Event.Record;

import java.time.OffsetDateTime;

public record Event(
        String id,
        String source,
        String subject,
        OffsetDateTime time,
        M
) {
    public Event {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
    }
}
/**
 * public string $specVersion,
 *         public string $type,
 *         public string $source,
 *         public string $subject,
 *         public string $id,
 *         public CarbonImmutable $time,
 *         #[SerializedName('datacontenttype')]
 *         public string $dataContentType, // must match the type in the eventType
 *         public string $data,
 *         public string $deduplicationId, // you can use a ULID if you don't need
 *         public string $messageGroup,
 *         public string $shortContext, // same as searchKey
 *
 *         /** @var EventContextDataV1[] $contextData */
 *
public array $contextData //
 */