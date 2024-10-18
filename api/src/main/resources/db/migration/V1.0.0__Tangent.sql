CREATE TABLE tangent_events
(
    id                VARCHAR PRIMARY KEY,
    type              VARCHAR NOT NULL,
    spec_version      VARCHAR NOT NULL,
    source            VARCHAR,
    subject           VARCHAR,
    time              TIMESTAMPTZ,
    data_content_type VARCHAR,
    data              TEXT,
    message_group     VARCHAR,
    deduplication_id     VARCHAR,
    created_at TIMESTAMPTZ
);
-- PARTITION BY RANGE (created_at);

CREATE INDEX idx_tangent_events_created_at ON tangent_events (created_at);
CREATE INDEX idx_tangent_events_deduplication_id ON tangent_events (deduplication_id);


CREATE TABLE tangent_event_contexts
(
    id                VARCHAR PRIMARY KEY,
    key     VARCHAR,
    value   VARCHAR,
    event_id VARCHAR,
    created_at TIMESTAMPTZ
);
-- PARTITION BY RANGE (created_at);

CREATE INDEX idx_tangent_event_contexts_created_at ON tangent_event_contexts (created_at);
CREATE INDEX idx_tangent_event_contexts_key ON tangent_event_contexts (key);
CREATE INDEX idx_tangent_event_contexts_event_id ON tangent_event_contexts (event_id);