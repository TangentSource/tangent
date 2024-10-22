CREATE TABLE tangent_subscriptions
(
    id                 BIGINT CHECK (id >= 0) PRIMARY KEY,
    name              VARCHAR NOT NULL,
    code              VARCHAR NOT NULL,
    description       VARCHAR,
    module            VARCHAR,
    status            VARCHAR,
    created_at          TIMESTAMPTZ,
    updated_at          TIMESTAMPTZ
);


CREATE INDEX idx_tangent_subscriptions_created_at ON tangent_subscriptions (created_at);
CREATE INDEX  idx_tangent_subscriptions_code ON tangent_subscriptions (code);
CREATE INDEX  idx_tangent_subscriptions_status ON tangent_subscriptions (status);


CREATE TABLE tangent_subscription_event_types
(
    id       BIGINT CHECK (id >= 0) PRIMARY KEY,
    subscription_id  BIGINT CHECK (id >= 0),
    event_type      VARCHAR
);
-- PARTITION BY RANGE (created_at);

CREATE INDEX idx_tangent_subscription_event_types_event_type ON tangent_subscription_event_types (event_type);
CREATE INDEX idx_tangent_subscription_event_types_subscription_id ON tangent_subscription_event_types (subscription_id);