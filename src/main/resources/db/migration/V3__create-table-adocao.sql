CREATE TABLE adocao (
    id BIGSERIAL PRIMARY KEY,
    data_solicitacao DATE NOT NULL,
    status VARCHAR(20) NOT NULL,

    tutor_id BIGINT NOT NULL,
    pet_id BIGINT NOT NULL,

    CONSTRAINT fk_adocao_tutor
        FOREIGN KEY (tutor_id)
        REFERENCES tutor(id),

    CONSTRAINT fk_adocao_pet
        FOREIGN KEY (pet_id)
        REFERENCES pet(id)
);