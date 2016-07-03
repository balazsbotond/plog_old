ALTER TABLE public.tag
  ADD CONSTRAINT uq_tag_name UNIQUE (name);
