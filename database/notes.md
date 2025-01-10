# DB Notes

When users are any type of staff they must log in. If user is a guest, they just choose it at the GUI.
Each hotel must have a guest user in which all hotel customers use.
When hotel is created a guest user is added

Hotel class needs an update (needs the field string Amenities to match the table)

## Conventions

- Access Methods
 + insert_[table]
 + update_[table]
 + delete_[table]
 + select_[table]s_by_[field]
 + select_all_[table]s