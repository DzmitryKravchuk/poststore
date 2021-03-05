CREATE TABLE "user_account_details" (
	"id" int NOT NULL,
	"name" character varying NOT NULL,
	"adress" character varying,
	"phone" int,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_details_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_product" (
	"id" serial NOT NULL,
	"shipping_to" character varying,
	"shipping_type" character varying NOT NULL,
	"user_account_id" int NOT NULL,
	"cost" DECIMAL NOT NULL,
	"status" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT order_product_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "product" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"price" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT product_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_item" (
	"id" serial NOT NULL,
	"product_id" int NOT NULL,
	"quantity" int NOT NULL,
	"order_product_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT order_item_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "courier" (
	"id" serial NOT NULL,
	"shipping_from" character varying NOT NULL,
	"shipping_to" character varying NOT NULL,
	"price" DECIMAL NOT NULL,
	"user_account_id" int NOT NULL,
	"addressee" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT courier_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "mailing" (
	"id" serial NOT NULL,
	"mailing_type" character varying NOT NULL,
	"weight" DECIMAL NOT NULL,
	"user_account_id" int NOT NULL,
	"addressee" character varying NOT NULL,
	"country_id" int NOT NULL,
	"address" character varying NOT NULL,
	"price" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT mailing_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "polygraphy" (
	"id" serial NOT NULL,
	"image_format" character varying NOT NULL,
	"copy_count" int NOT NULL,
	"is_coloured" BOOLEAN NOT NULL,
	"is_duplex_printing" BOOLEAN NOT NULL,
	"price" DECIMAL NOT NULL,
	"user_account_id" int NOT NULL,
	"paper_details_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT polygraphy_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "money_transfer" (
	"id" serial NOT NULL,
	"payer_name" character varying NOT NULL,
	"payer_adress" character varying NOT NULL,
	"beneficiary_name" character varying NOT NULL,
	"beneficiary_adress" character varying NOT NULL,
	"amount" DECIMAL NOT NULL,
	"transaction_price" DECIMAL NOT NULL,
	"user_account_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT money_transfer_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "store" (
	"id" serial NOT NULL,
	"store_type" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT store_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"e_mail" character varying NOT NULL,
	"password" character varying NOT NULL,
	"user_role" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "inventory" (
	"id" serial NOT NULL,
	"product_id" int NOT NULL,
	"store_id" int NOT NULL,
	"quantity" int NOT NULL,
	"version" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT inventory_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "letter_zone" (
	"id" serial NOT NULL,
	"price4g100" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT letter_zone_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"express_zone_id" int NOT NULL,
	"letter_zone_id" int NOT NULL,
	"parcel_zone_id" int NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "parcel_zone" (
	"id" serial NOT NULL,
	"price4g100" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT parcel_zone_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "express_zone" (
	"id" serial NOT NULL,
	"price4g100" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT express_zone_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "paper_details" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"price" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT paper_details_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user_account_details" ADD CONSTRAINT "user_account_details_fk0" FOREIGN KEY ("id") REFERENCES "user_account"("id");

ALTER TABLE "order_product" ADD CONSTRAINT "order_product_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");


ALTER TABLE "order_item" ADD CONSTRAINT "order_item_fk0" FOREIGN KEY ("product_id") REFERENCES "product"("id");
ALTER TABLE "order_item" ADD CONSTRAINT "order_item_fk1" FOREIGN KEY ("order_product_id") REFERENCES "order_product"("id");

ALTER TABLE "courier" ADD CONSTRAINT "courier_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");

ALTER TABLE "mailing" ADD CONSTRAINT "mailing_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
ALTER TABLE "mailing" ADD CONSTRAINT "mailing_fk1" FOREIGN KEY ("country_id") REFERENCES "country"("id");

ALTER TABLE "polygraphy" ADD CONSTRAINT "polygraphy_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
ALTER TABLE "polygraphy" ADD CONSTRAINT "polygraphy_fk1" FOREIGN KEY ("paper_details_id") REFERENCES "paper_details"("id");

ALTER TABLE "money_transfer" ADD CONSTRAINT "money_transfer_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");



ALTER TABLE "inventory" ADD CONSTRAINT "inventory_fk0" FOREIGN KEY ("product_id") REFERENCES "product"("id");
ALTER TABLE "inventory" ADD CONSTRAINT "inventory_fk1" FOREIGN KEY ("store_id") REFERENCES "store"("id");


ALTER TABLE "country" ADD CONSTRAINT "country_fk0" FOREIGN KEY ("express_zone_id") REFERENCES "express_zone"("id");
ALTER TABLE "country" ADD CONSTRAINT "country_fk1" FOREIGN KEY ("letter_zone_id") REFERENCES "letter_zone"("id");
ALTER TABLE "country" ADD CONSTRAINT "country_fk2" FOREIGN KEY ("parcel_zone_id") REFERENCES "parcel_zone"("id");




