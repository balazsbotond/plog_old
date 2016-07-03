-- :name get-posts :? :*
-- :doc gets a page of posts, in reverse chronological order
select
    post.id
  , created
  , last_modified
  , text
  , array_agg(tag.name) filter (where tag.name is not null) as tags
from post
left join post_tag on
    post.id = post_tag.post_id
left join tag on
    post_tag.tag_id = tag.id
group by
    post.id
  , created
  , last_modified
  , text
order by created desc
limit :limit
offset :offset;

-- :name create-post! :! :n
-- :doc creates a new post
insert into post (text)
values (:text);

-- :name update-post! :! :n
-- :doc update an existing post
update post
set text = :text,
    last_modified = timezone('utc'::text, now())
where id = :id;

-- :name delete-post! :! :n
-- :doc deletes a post given the id
delete from post
where id = :id;
