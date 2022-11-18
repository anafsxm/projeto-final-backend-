CREATE TABLE item_pedido (
  id bigserial NOT NULL,
  qt int8 NOT NULL,
  pedido_id int8,
  produto_id int8,
  PRIMARY KEY (id)
);
CREATE TABLE pedido (
  id bigserial NOT NULL,
  data_pedido timestamp,
  status varchar(255),
  user_id int8,
  PRIMARY KEY (id)
);
CREATE TABLE produto (
  id bigserial NOT NULL,
  certificacoes varchar(255),
  descricao varchar(255),
  nome varchar(255),
  quantidade int8 NOT NULL,
  valor numeric(19, 2) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE usuario (
  id bigserial NOT NULL,
  email varchar(255),
  nome varchar(255),
  senha varchar(255),
  telefone varchar(255),
  tipo_usuario varchar(255),
  PRIMARY KEY (id)
);
ALTER TABLE item_pedido ADD CONSTRAINT FK60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id) REFERENCES pedido;
ALTER TABLE item_pedido ADD CONSTRAINT FKtk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id) REFERENCES produto;
ALTER TABLE pedido ADD CONSTRAINT FKrfl7hanr633isps0k9lmj678i FOREIGN KEY (user_id) REFERENCES usuario;

CREATE TABLE carrinho (
  id bigserial NOT NULL,
  usuario_id int8,
  PRIMARY KEY (id)
);
CREATE TABLE item_carrinho (
  id bigserial NOT NULL,
  qt int8 NOT NULL,
  carrinho_id int8,
  produto_id int8,
  PRIMARY KEY (id)
);
ALTER TABLE carrinho ADD CONSTRAINT FK8jwo8e9vk1gdcw8ak7if31ahc FOREIGN KEY (usuario_id) REFERENCES usuario;
ALTER TABLE item_carrinho ADD CONSTRAINT FKr3dusq21jhlttwc4hanxhlbua FOREIGN KEY (carrinho_id) REFERENCES carrinho;
ALTER TABLE item_carrinho ADD CONSTRAINT FK7he6x1mtdwm4fmlsa09yxjifx FOREIGN KEY (produto_id) REFERENCES produto;

CREATE TABLE mesa (
  id bigserial NOT NULL,
  qt_lugares int8 NOT NULL,
  reservada boolean NOT NULL,
  restaurante_id int8,
  PRIMARY KEY (id)
);
CREATE TABLE restaurante (
  id bigserial NOT NULL,
  cidade varchar(255),
  nome varchar(255),
  PRIMARY KEY (id)
);
ALTER TABLE mesa ADD CONSTRAINT FKpebnxug9x5va47ryok0buyesb FOREIGN KEY (restaurante_id) REFERENCES restaurante;


create table reserva (id  bigserial not null, dia_reservado timestamp, qt_pessoas int8 not null, mesa_id int8, usuario_id int8, primary key (id));
alter table reserva add constraint FKpl3xwkxn9q87uwban10i1c9kk foreign key (mesa_id) references mesa;
alter table reserva add constraint FKiad9w96t12u3ms2ul93l97mel foreign key (usuario_id) references usuario;


insert into restaurante values (1,'Uberlândia', 'Byte') ,(2,'Patos de Minas', 'MegaByte'),(3,'São Paulo', 'GigaByte');

insert into mesa values (1,4,false,1), (2,2,false,1), (3,8,false,1);
insert into mesa values (4,4,false,2), (5,2,false,2), (6,8,false,2);
insert into mesa values (7,4,false,3), (8,2,false,3), (9,8,false,3), (10,2,false,3);


alter table pedido add column codigo_rastreio varchar(255);