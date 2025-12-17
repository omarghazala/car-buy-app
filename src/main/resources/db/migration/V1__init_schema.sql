-- Create customer_request table
CREATE TABLE customer_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    checked_by_company VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT chk_status CHECK (status IN ('PENDING', 'ACTIVE', 'CLOSED')),
    CONSTRAINT chk_company CHECK (checked_by_company IN ('AUTO_CHECK_CO', 'VEHI_VERIFY_INC'))
);

-- Create supplier_offer table
CREATE TABLE supplier_offer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    supplier_id BIGINT NOT NULL,
    customer_request_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    inspection_score INT,
    car_details TEXT NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_supplier_offer_request FOREIGN KEY (customer_request_id)
        REFERENCES customer_request(id) ON DELETE CASCADE,
    CONSTRAINT chk_offer_status CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),
    CONSTRAINT chk_inspection_score CHECK (inspection_score IS NULL OR (inspection_score >= 0 AND inspection_score <= 100)),
    CONSTRAINT chk_price CHECK (price > 0),
    CONSTRAINT uk_supplier_request UNIQUE (supplier_id, customer_request_id)
);