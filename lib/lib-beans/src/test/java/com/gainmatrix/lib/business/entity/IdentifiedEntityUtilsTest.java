package com.gainmatrix.lib.business.entity;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IdentifiedEntityUtilsTest {

    @Test
    public void testExtractIdsFromCollection() throws Exception {
        SampleBusinessEntity entity1 = new SampleBusinessEntity(1);
        SampleBusinessEntity entity2 = new SampleBusinessEntity(2);
        SampleBusinessEntity entity3 = new SampleBusinessEntity(3);
        SampleBusinessEntity entity4 = new SampleBusinessEntity(4);
        SampleBusinessEntity entity5 = new SampleBusinessEntity(5);

        List<? extends BusinessEntity<Long>> list = Arrays.asList(
               entity2, entity5, entity1, entity4, entity3
        );

        Set<Long> result = IdentifiedEntityUtils.extractIds(list);
        Assert.assertNotNull(result);
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains(1L));
        Assert.assertTrue(result.contains(2L));
        Assert.assertTrue(result.contains(3L));
        Assert.assertTrue(result.contains(4L));
        Assert.assertTrue(result.contains(5L));
        Assert.assertFalse(result.contains(6L));
    }

    @Test
    public void testIsSameId() throws Exception {
        SampleBusinessEntity entity1 = new SampleBusinessEntity(1);
        SampleBusinessEntity entity2 = new SampleBusinessEntity(2);
        SampleBusinessEntity entity3 = new SampleBusinessEntity(1);

        Assert.assertFalse(IdentifiedEntityUtils.isSameId(entity1, entity2));
        Assert.assertTrue(IdentifiedEntityUtils.isSameId(entity1, entity3));
    }

    @Test
    public void testIsEqual() throws Exception {
        SampleBusinessEntity entity1 = new SampleBusinessEntity(1);
        BusinessEntityUtils.updateBusinessId(entity1, BusinessId.fromString("d2ff6b79-abb0-4377-bee3-04294bc92091"));
        SampleBusinessEntity entity2 = new SampleBusinessEntity(2);
        BusinessEntityUtils.updateBusinessId(entity2, BusinessId.fromString("d5e76925-3e10-4294-a92c-ace052c39cbc"));
        SampleBusinessEntity entity3 = new SampleBusinessEntity(3);
        BusinessEntityUtils.updateBusinessId(entity3, BusinessId.fromString("d2ff6b79-abb0-4377-bee3-04294bc92091"));

        Assert.assertFalse(IdentifiedEntityUtils.isEqual(entity1, entity2));
        Assert.assertTrue(IdentifiedEntityUtils.isEqual(entity1, entity3));
    }

    @Test
    public void testUpdateBusinessModelVersion() throws Exception {
        SampleBusinessEntity entity = new SampleBusinessEntity(1);
        Assert.assertEquals(1L, (long) entity.getId());
        BusinessEntityUtils.updateBusinessModelVersion(entity, 123);
        Assert.assertEquals(123, entity.getBusinessModelVersion());
    }

    @Test
    public void testUpdateVersion() throws Exception {
        SampleBusinessEntity entity = new SampleBusinessEntity(1);
        Assert.assertEquals(1L, (long) entity.getId());
        BusinessEntityUtils.updateVersion(entity, 123);
        Assert.assertEquals(123, entity.getVersion());
    }

    @Test
    public void testExtractIdsFromArray() throws Exception {
        SampleBusinessEntity entity1 = new SampleBusinessEntity(1);
        SampleBusinessEntity entity2 = new SampleBusinessEntity(2);
        SampleBusinessEntity entity3 = new SampleBusinessEntity(3);
        SampleBusinessEntity entity4 = new SampleBusinessEntity(4);
        SampleBusinessEntity entity5 = new SampleBusinessEntity(5);

        SampleBusinessEntity[] array = {
               entity2, entity5, entity1, entity4, entity3
        };

        Set<Long> result = IdentifiedEntityUtils.extractIds(array);
        Assert.assertNotNull(result);
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains(1L));
        Assert.assertTrue(result.contains(2L));
        Assert.assertTrue(result.contains(3L));
        Assert.assertTrue(result.contains(4L));
        Assert.assertTrue(result.contains(5L));
        Assert.assertFalse(result.contains(6L));
    }

    @Test
    public void testMapById() throws Exception {
        SampleBusinessEntity entity1 = new SampleBusinessEntity(1);
        SampleBusinessEntity entity2 = new SampleBusinessEntity(2);
        SampleBusinessEntity entity3 = new SampleBusinessEntity(3);
        SampleBusinessEntity entity4 = new SampleBusinessEntity(4);
        SampleBusinessEntity entity5 = new SampleBusinessEntity(5);

        List<? extends BusinessEntity<Long>> list = Arrays.asList(
               entity2, entity5, entity1, entity4, entity3
        );

        Map<Long, ? extends BusinessEntity<Long>> map = IdentifiedEntityUtils.mapById(list);
        Assert.assertNotNull(map);
        Assert.assertEquals(5, map.size());
        Assert.assertEquals(entity1, map.get(1L));
        Assert.assertEquals(entity2, map.get(2L));
        Assert.assertEquals(entity3, map.get(3L));
        Assert.assertEquals(entity4, map.get(4L));
        Assert.assertEquals(entity5, map.get(5L));
    }

    private static class SampleBusinessEntity extends AbstractBusinessEntity<Long> {

        private Long id;

        private SampleBusinessEntity(long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public int getCurrentBusinessModelVersion() {
            return AbstractBusinessEntity.BUSINESS_MODEL_VERSION_UNDEFINED;
        }
    }

}
