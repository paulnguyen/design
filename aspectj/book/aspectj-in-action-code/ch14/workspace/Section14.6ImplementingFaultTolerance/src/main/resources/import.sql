insert into products (id, name, description, price) values(1001,'ProductA', 'Product A description', 1)
insert into products (id, name, description, price) values(1002,'ProductB', 'Product B description', 2)
insert into products (id, name, description, price) values(1003,'ProductC', 'Product C description', 3)
insert into products (id, name, description, price) values(1004,'ProductD', 'Product D description', 4)

insert into orders(id, placed) values(2001, 'y')
insert into orders(id, placed) values(2002, 'n')

insert into lineItems (id, quantity, product_id, unitPrice, order_Id) values(1, 1, 1001, 1, 2001)
insert into lineItems (id, quantity, product_id, unitPrice, order_Id) values(2, 2, 1002, 2, 2001)

insert into inventoryItems(id, product_id, quantityOnHand) values(1, 1001, 0);
insert into inventoryItems(id, product_id, quantityOnHand) values(2, 1002, 0);