ALTER TABLE public.tag
  DROP CONSTRAINT uq_tag_name UNIQUE (name);
